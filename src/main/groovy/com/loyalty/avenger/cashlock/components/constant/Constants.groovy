package com.loyalty.avenger.cashlock.components.constant

class Constants {

    final static String TEMPLATE_FOLDER = "src/main/resources/template"
    final static String EMAIL_OPT_OUT_GET_TEMPLATE_ENGLISH = "$TEMPLATE_FOLDER/optout/GetEnglish.html"
    final static String EMAIL_OPT_OUT_GET_UNSUBSCRIBE_TEMPLATE_ENGLISH = "$TEMPLATE_FOLDER/optout/GetUnsubscribeEnglish.html"
    final static String EMAIL_OPT_OUT_POST_UNSUBSCRIBE_TEMPLATE_ENGLISH = "$TEMPLATE_FOLDER/optout/PostUnsubscribeEnglish.html"
    final static String EMAIL_OPT_OUT_POST_UNSUBSCRIBE_TEMPLATE_FRENCH = "$TEMPLATE_FOLDER/optout/PostUnsubscribeFrench.html"
    final static String EMAIL_OPT_OUT_GET_UNSUBSCRIBE_TEMPLATE_FRENCH = "$TEMPLATE_FOLDER/optout/GetUnsubscribeFrench.html"
    final static String EMAIL_OPT_OUT_POST_UNSUBSCRIBEERR_TEMPLATE_ENGLISH = "$TEMPLATE_FOLDER/optout/PostUnsubscribeEnglishErr.html"
    final static String EMAIL_OPT_OUT_POST_UNSUBSCRIBEERR_TEMPLATE_FRENCH = "$TEMPLATE_FOLDER/optout/PostUnsubscribeFrenchErr.html"

    //final static String EMAIL_OPT_OUT_POST_ACC_TEMPLATE_ENGLISH = "$TEMPLATE_FOLDER/optout/PostACCEnglish.html"
    final static String EMAIL_OPT_OUT_POST_TEMPLATE_ENGLISH = "$TEMPLATE_FOLDER/optout/PostEnglish.html"
    final static String EMAIL_OPT_OUT_GET_TEMPLATE_FRENCH = "$TEMPLATE_FOLDER/optout/GetFrench.html"
    final static String EMAIL_OPT_OUT_POST_TEMPLATE_FRENCH = "$TEMPLATE_FOLDER/optout/PostFrench.html"
    final static String ENROLL_TEMPLATE_ENGLISH = "$TEMPLATE_FOLDER/notify/EnrollEnglish.html"
    final static String ENROLL_TEMPLATE_FRENCH = "$TEMPLATE_FOLDER/notify/EnrollFrench.html"

    final static String EMAIL_CHANGE_TEMPLATE_ENGLISH = "$TEMPLATE_FOLDER/notify/EmailChangeEnglish.html"
    final static String EMAIL_CHANGE_TEMPLATE_FRENCH = "$TEMPLATE_FOLDER/notify/EmailChangeFrench.html"

    final static String OLD_EMAIL_CHANGE_TEMPLATE_ENGLISH = "$TEMPLATE_FOLDER/notify/OldEmailChangeEnglish.html"
    final static String OLD_EMAIL_CHANGE_TEMPLATE_FRENCH = "$TEMPLATE_FOLDER/notify/OldEmailChangeFrench.html"

    final static String NO_EMAIL_CHANGE_TEMPLATE_ENGLISH = "$TEMPLATE_FOLDER/notify/NoEMailChangeEnglish.html"
    final static String NO_EMAIL_CHANGE_TEMPLATE_FRENCH = "$TEMPLATE_FOLDER/notify/NoEMailChangeFrench.html"

    final static String DEFAULT_FIRST_NAME = "Robot"

    //EmailChangeEnglish.html
    final static String PERSONALIZE_TEMPLATE_ENGLISH = "$TEMPLATE_FOLDER/notify/PersonalizeEnglish.html"
    final static String PERSONALIZE_TEMPLATE_FRENCH = "$TEMPLATE_FOLDER/notify/PersonalizeFrench.html"

    final static String RSA = "RSA"
    final static String UTF_8 = "UTF-8"
    final static String ISO_8859_1 = "ISO-8859-1"
    final static String EXPECTED_EMAIL_SENDER = "LOYALTYONE, CO <loyaltyone-rt-stage2@adobe-campaign.com>"
    final static Integer NUMBER_OF_RETRIES = 20
    final static Integer WAIT_TIME_BETWEEN_RETRIES = 10000
    final static String DEFAULT_AGENT_ID ="007"
    final static String DEFAULT_SAML_PROFILE = "default"

    final static String LOCALE_ENGLISH = "en-CA"
    final static String LOCALE_FRENCH = "fr-CA"
    final static String LANGUAGE_ENGLISH = "ENGLISH"
    final static String LANGUAGE_FRENCH = "FRENCH"
    final static Integer UPDATE_TOKEN_DURATION_SECONDS = 7200
    final static Integer PERSONALIZE_DURATION_SECONDS = 86400
    final static Integer ENROLL_DURATION_SECONDS = 86400

    final static String VERIFY_EMAIL_SUBJECT_ENGLISH_ENROLLMENT = "[EXTERNAL] Complete your AIR MILES profile – verify your email address."
    final static String VERIFY_EMAIL_SUBJECT_FRENCH_ENROLLMENT = "[EXTERNAL] Remplissez les détails de votre profil – vérifiez votre adresse de courriel."

    final static String VERIFY_EMAIL_SUBJECT_ENGLISH_EMAIL_CHANGE= "[EXTERNAL] Your email address has been successfully updated."
    final static String VERIFY_EMAIL_SUBJECT_FRENCH_EMAIL_CHANGE= "[EXTERNAL] Votre adresse de courriel est en cours de vérification."

    final static String VERIFICATION_PENDING_EMAIL_SUBJECT_ENGLISH= "[EXTERNAL] Your email address verification is pending."
    final static String VERIFICATION_PENDING_EMAIL_SUBJECT_FRENCH= "[EXTERNAL] Votre adresse de courriel est en cours de vérification."



    final static String VERIFY_EMAIL_SUBJECT_ENGLISH_EMAIL_CHANGE_OLD= "[EXTERNAL] A request has been made to update the email address on your AIR MILES profile."
    final static String VERIFY_EMAIL_SUBJECT_FRENCH_EMAIL_CHANGE_OLD= "[EXTERNAL] Nous avons reçu une demande de mise à jour de l’adresse de courriel de votre profil AIR MILES."

    final static String VERIFY_EMAIL_OTHER_CHANGE_SUBJECT_ENGLISH = "[EXTERNAL] Your profile information has been updated."
    final static String VERIFY_EMAIL_OTHER_CHANGE_SUBJECT_FRENCH = "[EXTERNAL] Les informations de votre profil AIR MILES ont changé."
    final static String VERIFY_EMAIL_REMINDER_SUBJECT_ENGLISH = "[EXTERNAL] Don’t forget to verify your email address with AIR MILES."
    final static String VERIFY_EMAIL_REMINDER_Catchup_SUBJECT_ENGLISH ="[EXTERNAL] Reminder: Verify your AIR MILES email address."

    final static String DEFAULT_SECRET_PIN = "1218"
    final static String DEFAULT_CHANGE_SECRET_PIN = "4444"
    final static Integer AUTH_DEFAULT_EXPIRES_IN = 180
    final static String AUTH_DEFAULT_TOKEN_TYPE = "Bearer"

    final static String BUSINESS_RULE_ERROR_TYPE = "BUSINESS_RULE"
    final static String BUSINESS_RULE_ERROR_CODE_DC_NV = "ESB-MEM-DC-NV"

    final static String SUCCESS_BODY = "{\"notificationId\":\"${UUID.randomUUID().toString()}\",\"status\":200}"

    final static String EMAIL_UPDATE_EXPECTED_ENGLISH_BODY = "Hi ${FieldTemplate.FIELD_1.getValue()}, </p><p>This is just a little note to confirm that the email address linked to your AIR MILES<sup style=\"font-size: 75%; line-height: 75%;\">®</sup> Collector Profile has been successfully changed."
    final static String EMAIL_UPDATE_EXPECTED_FRENCH_BODY = "Bonjour ${FieldTemplate.FIELD_1.getValue()}, </p><p>Ceci est un message pour vous confirmer que l'adresse de courriel liée à votre profil d'adhérent AIR MILES<sup style=\"font-size: 75%; line-height: 75%;\">md</sup> a bien été modifiée."

    final static String OLD_EMAIL_UPDATE_EXPECTED_ENGLISH_BODY = "Hi ${FieldTemplate.FIELD_1.getValue()}, </p><p>We just got a request to change the email address linked to your AIR MILES<sup style=\"font-size: 75%; line-height: 75%;\">®</sup> Collector Profile to: <br><br><b>• ${FieldTemplate.FIELD_2.getValue()}</b><br><br>If this wasn't you, please do not reply to this email. Let us know through our"
    final static String OLD_EMAIL_UPDATE_EXPECTED_FRENCH_BODY = "Bonjour ${FieldTemplate.FIELD_1.getValue()}, </p><p>Nous venons de recevoir une demande de modification de votre adresse de courriel liée à votre profil d'adhérent AIR MILES<sup style=\"font-size: 75%; line-height: 75%;\">md</sup> pour : <br><br><b>• ${FieldTemplate.FIELD_2.getValue()}</b><br><br>Si ce n'était pas vous, veuillez ne pas répondre à ce courriel. Faites-le-nous savoir sur notre"

    final static String OTHER_UPDATE_EXPECTED_ENGLISH_BODY = "Hi ${FieldTemplate.FIELD_1.getValue()}, <br><br>This is just a little note to confirm that the personal information in your AIR MILES<sup style=\"font-size: 75%; line-height: 75%;\">®</sup> Collector Profile has changed. <br><br>The following information has been updated: <br><br><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-size: 16px; font-family: Calibri, Arial, Helvetica, sans-serif; line-height: 22px; color: #000000;\"><tbody><tr><td width=\"30\" align=\"right\" valign=\"top\">•&nbsp;</td><td align=\"left\" valign=\"top\">Mailing address</td></tr></tbody></table><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-size: 16px; font-family: Calibri, Arial, Helvetica, sans-serif; line-height: 22px; color: #000000;\"><tbody><tr><td width=\"30\" align=\"right\" valign=\"top\">•&nbsp;</td><td align=\"left\" valign=\"top\">Phone number</td></tr></tbody></table><br>If this wasn't you, please do not reply to this email. Let us know through our "
    final static String OTHER_UPDATE_EXPECTED_FRENCH_BODY = "Bonjour ${FieldTemplate.FIELD_1.getValue()}, <br><br>Ceci est un message pour vous confirmer que les informations personnelles de votre profil d'adhérent AIR MILES<sup style=\"font-size: 75%; line-height: 75%;\">md</sup> ont bien été modifiées. <br><br>Les informations suivantes ont été mises à jour : <br><br><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-size: 16px; font-family: Calibri, Arial, Helvetica, sans-serif; line-height: 22px; color: #000000;\"><tbody><tr><td width=\"30\" align=\"right\" valign=\"top\">•&nbsp;</td><td align=\"left\" valign=\"top\">Adresse postale"

    final static List<String> EXPECTED_EMAIL_UPDATE_CONTENT_ENGLISH = ["airmiles.ca/en/profile/verify"]
    final static List<String> EXPECTED_EMAIL_UPDATE_CONTENT_FRENCH = ["airmiles.ca/fr/profil/verification"]

    final static String NO_CHANNEL_PLEASE = "No Channel"

    final static String  SECRET_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtuXJCJD5OxKGCMCnKtD8\n" +
            "VlQ3G8ZawbpiaEEEEtDndNIaTJSmdZEfgnqEjffIiuxru+4geftQJ+ZKnVl31Q7q\n" +
            "q5R0RlLjmlPYCl7VccwgVxX148Jt+VACl1mZfbsSaUwdhLQYjSNfrcdfCmDJ138M\n" +
            "xz0quNnuz9M1VjxFNoE0q4IhAnX5Mq5AfZrEA8vm6Migf+O7yQrsJdEO273xyPVN\n" +
            "IrbMwU7R0vbFR+LzvI3VfZnVsuDLjcIbWNI20fA+pjVkMWwf39jxvTCBGfssZISV\n" +
            "fejMwucPYdxK0IsWzYmJrox2Kk7ZO3ShfvsBe+V69O+9YMdx440tpfBtRBDz34pH\n" +
            "FQIDAQAB\n" +
            "-----END PUBLIC KEY-----"

}
