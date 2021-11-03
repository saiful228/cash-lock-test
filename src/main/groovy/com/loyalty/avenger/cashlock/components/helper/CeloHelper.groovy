package com.loyalty.avenger.cashlock.components.helper
import com.loyalty.avenger.cashlock.components.constant.Channels
import com.loyalty.avenger.cashlock.components.util.TestDataUtils
import com.loyalty.ws.cpm.esb.amrp._2._1.common.RequestContextType
import com.loyalty.ws.cpm.esb.amrp._2._1.member.*
import com.loyalty.ws.cpm.esb.amrp._2._1.member.types.EnrollMemberRequest
import com.loyalty.ws.cpm.esb.amrp._2._1.member.types.GetMemberProfileV2Request
import com.loyalty.ws.cpm.esb.amrp._2._1.member.types.UpdateMemberProfileRequest

import java.time.LocalDate

class CeloHelper {
    /*******************************************
     *
     *    Enroll Member Default Request
     *
     ******************************************/
    static EnrollMemberRequest getDefaultEnrollmentRequest() {
        EnrollMemberRequest enrollMemberRequest = new EnrollMemberRequest()
        enrollMemberRequest.setRequestContext(getDefaultRequestContextType())

        /**       Program Details         **/
        ProgramDetailsType programDetails = new ProgramDetailsType()
        programDetails.setEnrollmentScheme("INDENREWEB")
        programDetails.setMembershipType("I")
        enrollMemberRequest.setProgramDetails(programDetails)

        /**       Personal Details         **/
        PersonalDetailsType personalDetailsType = new PersonalDetailsType()
        personalDetailsType.setFirstName(TestDataUtils.getRandomString(25))  // Divide total length by 3, so name is not 75 chars long
        personalDetailsType.setLastName(TestDataUtils.getRandomString(25))   // Divide total length by 3, so name is not 75 chars long
        personalDetailsType.setDateOfBirth(TestDataUtils.getXmlGregorianDate(LocalDate.now().minusYears(TestDataUtils.getRandomNumberBetween(20, 55))))
        enrollMemberRequest.setPersonalDetails(personalDetailsType)
        enrollMemberRequest.setContactDetails(getEnrollmentContact())

        /**     Home Address   **/
        enrollMemberRequest.setHomeAddress(setDefaultValidAddress())  // <- Set Default Valid Address

        /**     Preference Details    **/
        PreferenceDetailsType preferenceDetailsType = new PreferenceDetailsType()
        preferenceDetailsType.setBalanceSupression(true)
        preferenceDetailsType.setInstantRedemptionSupression(true)
        enrollMemberRequest.setPreferenceDetails(preferenceDetailsType)
        enrollMemberRequest.setUpdateFlag(false)
        enrollMemberRequest.setEventType("TR_EN")
        enrollMemberRequest
    }

    //Default Request Context Header
    static RequestContextType getDefaultRequestContextType() {
        RequestContextType context = new RequestContextType()
        context.setChannel Channels.WEB.getValue()
        context.setSource 'INVEMLCAMP'
        context.setLocale ''
        context.setUser 'ONLINEUSER'

        //Returns the context
        context
    }

    static AddressType setDefaultValidAddress() {
        AddressType addressType = new AddressType()
        addressType.setAddressLine1("438 University Ave")
        addressType.setCity("Toronto")
        addressType.setCountry("CAN")
        addressType.setState("ON")
        addressType.setPostalCode("M5G2K8")
        addressType
    }

    static MemberIdentifierType getDefaultMemberIdentifier(String cardNUmber) {
        MemberIdentifierType memberIdentifierType =  new MemberIdentifierType()
        memberIdentifierType.cardNumber = cardNUmber
        memberIdentifierType
    }

    static ContactDetailsType getEnrollmentContact() {
        ContactDetailsType contactDetailsType = new ContactDetailsType()
        contactDetailsType.email = TestDataUtils.getRandomValidEmail()
        contactDetailsType.validateFlag = true
        contactDetailsType
    }

    static UpdateContactDetailsType getEmailUpdateContact(String email) {
        UpdateContactDetailsType contactDetailsType = new UpdateContactDetailsType()
        contactDetailsType.email = email
        contactDetailsType.validateFlag = true
        contactDetailsType.emailVerifiedFlag = true
        contactDetailsType
    }

    static UpdateMemberProfileRequest getEmailUpdateSOAPRequest(String cardNumber, String email, String channel = Channels.WEB.getValue()) {
        UpdateMemberProfileRequest request = new UpdateMemberProfileRequest()
        request.requestContext = getDefaultRequestContextType()
        request.getRequestContext().channel = channel
        request.memberIdentifier = getDefaultMemberIdentifier(cardNumber)
        request.contactDetails = getEmailUpdateContact(email)
        request
    }

    static GetMemberProfileV2Request getDefaultGetMemberProfileRequest(String cardNumber) {
        GetMemberProfileV2Request getMemberProfileRequest = new GetMemberProfileV2Request()
        getMemberProfileRequest.setRequestContext(getDefaultRequestContextType())
        getMemberProfileRequest.cardNumber = cardNumber
        return getMemberProfileRequest
    }

//    static GetMemberProfileV2Request getDefaultMemberProfileRequest(String cardNumber) {
//        GetMemberProfileV2Request getMemberProfileRequest = new GetMemberProfileV2Request()
//        getMemberProfileRequest.setRequestContext(defaultRequestContextType())
//        getMemberProfileRequest.cardNumber = cardNumber
//        return getMemberProfileRequest
//    }

    static UpdateMemberProfileRequest getDefaultUpdateMemberRequest(String cardNumber)  {
        UpdateMemberProfileRequest updateMemberProfileRequest = new UpdateMemberProfileRequest()
        updateMemberProfileRequest.setRequestContext(getDefaultRequestContextType())
        MemberIdentifierType memberIdentifierType = new MemberIdentifierType()
        memberIdentifierType.setCardNumber(cardNumber)
        updateMemberProfileRequest.setMemberIdentifier(memberIdentifierType)
        UpdatePreferenceDetailsType preferenceDetails = new UpdatePreferenceDetailsType()
        updateMemberProfileRequest.setPreferenceDetails(preferenceDetails)
        updateMemberProfileRequest
    }

}
