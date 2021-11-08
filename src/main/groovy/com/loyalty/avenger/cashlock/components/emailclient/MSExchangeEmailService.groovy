package com.loyalty.avenger.cashlock.components.emailclient

import com.loyalty.avenger.cashlock.components.type.EMailMessageType
import groovy.util.logging.Slf4j
import microsoft.exchange.webservices.data.core.ExchangeService
import microsoft.exchange.webservices.data.core.PropertySet
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName
import microsoft.exchange.webservices.data.core.enumeration.service.DeleteMode
import microsoft.exchange.webservices.data.core.service.folder.Folder
import microsoft.exchange.webservices.data.core.service.item.EmailMessage
import microsoft.exchange.webservices.data.core.service.item.Item
import microsoft.exchange.webservices.data.credential.ExchangeCredentials
import microsoft.exchange.webservices.data.credential.WebCredentials
import microsoft.exchange.webservices.data.property.complex.ItemId
import microsoft.exchange.webservices.data.search.FindItemsResults
import microsoft.exchange.webservices.data.search.ItemView
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Slf4j
@Lazy
@Component
@EnableAutoConfiguration
class MSExchangeEmailService {

    @Value ('${loyalty.email-box.uri}')
    String uri

    @Value ('${loyalty.email-box.email}')
    String email

    @Value ('${loyalty.email-box.password}')
    String password

    static ExchangeService service
    static Integer NUMBER_EMAILS_FETCH = 1000

    ExchangeService getService() {
        ExchangeCredentials credentials = new WebCredentials(email, Encryptor.decrypt(password), "")
        service = new ExchangeService(ExchangeVersion.Exchange2010_SP2)
        service.setUrl(new URI(uri))
        service.setCredentials(credentials)
        service
    }

    def cleanEMailFolder(WellKnownFolderName folderName) {
        if( isEmail(folderName,false) ==  true ) {
            List<EMailMessageType> messageList = readEmails(folderName, false)
            if( messageList != null && messageList.size() > 0 ) {
                deleteEMailMessageList(messageList)
            }
        }
    }

    List<EMailMessageType> readEmails(WellKnownFolderName folderName, Boolean applySenderFilter = true) {
        List<EMailMessageType> msgDataList = new ArrayList<EMailMessageType>()
        try {
            Object lock = new Object()
            FindItemsResults results
            synchronized (lock) {
                Folder folder = Folder.bind(getService(), folderName)
                for (int i = 0; i < Constants.NUMBER_OF_RETRIES; i++) {
                    results = getService().findItems(folder.getId(), new ItemView(NUMBER_EMAILS_FETCH))
                    if (results.size() > 0) {
                        results.each {
                            EMailMessageType eMailMessage = readEmailItem(it.getId())
                            if(applySenderFilter) {
                                if(eMailMessage.fromAddress.equalsIgnoreCase(Constants.EXPECTED_EMAIL_SENDER)) {
                                    msgDataList.add(eMailMessage)
                                }
                            }
                            else {
                                msgDataList.add(eMailMessage)
                            }
                        }
                        break
                    } else {
                        Thread.sleep(Constants.WAIT_TIME_BETWEEN_RETRIES)
                    }
                }
            }
        }
        catch (Exception e) {
            Logger.logError(e.message.toString())
        }
        msgDataList
    }

    Boolean isEmail(WellKnownFolderName folderName, Boolean applySenderFilter = true) {
        Boolean result = false
        try {
            Object lock = new Object()
            FindItemsResults results
            synchronized (lock) {
                Folder folder = Folder.bind(getService(), folderName)
                for (int i = 0; i < 3; i++) {
                    results = getService().findItems(folder.getId(), new ItemView(NUMBER_EMAILS_FETCH))
                    if (results.size() > 0) {
                        results.each {
                            EMailMessageType eMailMessage = readEmailItem(it.getId())
                            if(applySenderFilter) {
                                if(eMailMessage.fromAddress.equalsIgnoreCase(Constants.EXPECTED_EMAIL_SENDER)) {
                                    result = true
                                }
                            }
                            else {
                                result = true
                            }
                        }
                        break
                    } else {
                        Thread.sleep(Constants.WAIT_TIME_BETWEEN_RETRIES)
                    }
                }
            }
        }
        catch (Exception e) {
            Logger.logError(e.message.toString())
        }
        result
    }



    EMailMessageType readEmailItem(ItemId itemId) {
        EMailMessageType msgData = new EMailMessageType()
        try {
            Item item = Item.bind(service, itemId, PropertySet.FirstClassProperties)
            EmailMessage emailMessage = EmailMessage.bind(service, item.id)
            msgData.emailItemId = emailMessage.getId().toString()
            msgData.subject = emailMessage.getSubject()
            msgData.fromAddress = emailMessage.getFrom().toString()
            msgData.senderName = emailMessage.getSender().getName()
            msgData.senddDate = emailMessage.getDateTimeCreated().toLocalDateTime()
            msgData.receivedDate = emailMessage.getDateTimeReceived().toLocalDateTime()
            msgData.emailBody = emailMessage.getBody().toString()
            msgData.emailMessage = emailMessage
        }
        catch (Exception e) {
            Logger.logError(e.getMessage())
        }
        msgData
    }

    def deleteEmailMessage(EMailMessageType message) {
        message.emailMessage.delete(DeleteMode.MoveToDeletedItems)
    }

    def deleteEMailMessageList(List<EMailMessageType> messageList) {
        messageList.each {
            deleteEmailMessage(it)
        }
    }
}
