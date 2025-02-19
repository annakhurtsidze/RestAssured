package Models.SMSModel.PostConsent;

public class PostSMSRequestModel {
        public String personId;
        public String TelNumber;
        public String status;
        public String channelId;

        @Override
        public String toString() {
                return "PostSMSRequestModel{" +
                        "personId='" + personId + '\'' +
                        ", TelNumber='" + TelNumber + '\'' +
                        ", status='" + status + '\'' +
                        ", channelId='" + channelId + '\'' +
                        '}';
        }



        public String getPersonId() {
                return personId;
        }

        public void setPersonId(String personId) {
                this.personId = personId;
        }

        public String getTelNumber() {
                return TelNumber;
        }

        public void setTelNumber(String telNumber) {
                TelNumber = telNumber;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public String getChannelId() {
                return channelId;
        }

        public void setChannelId(String channelId) {
                this.channelId = channelId;
        }



}
