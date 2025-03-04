package Models.SMSModel.GetConsent;

public class GetSMSRequestModel {
    public String TelNumber;
    public String personId;
    public String Consent;

    public String getConsent() {
        return Consent;
    }

    public void setConsent(String consent) {
        Consent = consent;
    }

    public String getTelNumber() {
        return TelNumber;
    }

    public void setTelNumber(String telNumber) {
        this.TelNumber = telNumber;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

}
