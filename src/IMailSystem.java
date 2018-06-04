public interface IMailSystem {
    void saveChanges(Mailbox mailbox);
    Mailbox findMailbox(String ext);
    IPersistence getPersistence();
    int getMailBoxCount();

}
