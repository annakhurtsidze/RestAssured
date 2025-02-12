package Models.SMSModel;

public class GetSMSResponseModel {
    public Data data;
    public Object message;
    public Object detailsMessage;
    public int externalState;
    public int state;
    public Object errorCode;
    public Object validationErrors;

    public Object getDetailsMessage() {
        return detailsMessage;
    }

    public void setDetailsMessage(Object detailsMessage) {
        this.detailsMessage = detailsMessage;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public Object getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Object validationErrors) {
        this.validationErrors = validationErrors;
    }

    public int getExternalState() {
        return externalState;
    }

    public void setExternalState(int externalState) {
        this.externalState = externalState;
    }

}
