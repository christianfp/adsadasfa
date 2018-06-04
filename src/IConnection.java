public interface IConnection {

    Mailbox getCurrentMailbox();
    void resetConnection();
    void setMailbox(Mailbox mailbox);
    void setStatus(IState state);
    void saveChanges();
    IMailSystem getMailboxSystem();
    void setModelView(IModelView modelView);
    void setError(IResponse connectResponseError);

    boolean executeCommand(IRequest request);
}
