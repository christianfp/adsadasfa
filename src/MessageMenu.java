public class MessageMenu implements IState {
    private Mailbox currentMailbox;
    private IConnection connection;
    public MessageMenu(IConnection connection){
        this.connection=connection;
        this.currentMailbox=connection.getCurrentMailbox();
        showMessageMenuOptions();
    }
    public boolean dial(String command) {

        if ("1".equals(command)) {
            showMessageText();
        } else if ("2".equals(command)) {
            saveCurrentMessage();
            showMessageMenuOptions();
        } else if ("3".equals(command)) {
            removeCurrentMessage();
            showMessageMenuOptions();
        } else if ("4".equals(command)) {
            changeToMailboxMenuState();

        }
        return true;
    }

    private void changeToMailboxMenuState() {
        connection.setStatus(new MailboxMenu(connection));
    }

    private void removeCurrentMessage() {
        currentMailbox.removeCurrentMessage();
        connection.saveChanges();
    }

    private void saveCurrentMessage() {
        currentMailbox.saveCurrentMessage();
        connection.saveChanges();
    }

    private void showMessageText() {
        String text=getTextOfLastMessage();
        IModelView modelView=new MessageMenuModelView(text);
        connection.setModelView(modelView);
    }

    private String getTextOfLastMessage() {
        String output="";
        Message m = currentMailbox.getCurrentMessage();
        if (m == null) {

            output="No messages.";

        }
        else {
            output = m.getText();

        }
        return output;
    }
    public boolean hangup() {
        connection.resetConnection();
        return true;
    }
    private void showMessageMenuOptions() {
        connection.setModelView(new MessageMenuModelView());
    }

}
