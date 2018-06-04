public class Connection implements IConnection
{
    public Mailbox getCurrentMailbox() {
        return currentMailbox;
    }
    public IMailSystem getMailboxSystem() {
        return system;
    }
    public void setMailbox(Mailbox mailbox) {
        this.currentMailbox = mailbox;
    }
    public void setStatus(IState state)
    {
        this.status=state;
    }
    private IMailSystem system;
    private IPresentersManager presentersManager;
    private Mailbox currentMailbox;
    private IState status;
   public Connection(IMailSystem s, IPresentersManager presentersManager)
   {
       system = s;
       this.presentersManager = presentersManager;

   }

   public void resetConnection()
   {
       currentMailbox=null;
       setPersistenceType();
       status=new Connect(this);
   }

    public boolean executeCommand(String input)
    {
        if (isInputHangUpCommand(input))
            return hangup();
        else if (isQuitCommand(input))
            return false;
        else if (input.equals("00000"))
            return changePeristence();
        else
            return dial(input);
    }
    public void saveChanges() {
       system.saveChanges(currentMailbox);
    }

    private Boolean hangup()
    {
        return status.hangup();
    }
   public boolean dial(String key)
   {
          return status.dial(key);
   }

   public boolean isConnected() {
	   return status instanceof Connect;
   }

   public boolean isRecording() {
	   return status instanceof Recording;
   }

   public boolean isChangePassCode() {
	   return status instanceof ChangePasscode;
   }

   public boolean isChangeGreeting() {
	   return status instanceof ChangeGreating;
   }

   public boolean isMailBoxMenu() {
	   return status instanceof MailboxMenu;
   }

   public boolean isMessageMenu() {
	   return status instanceof MessageMenu;
   }


    private boolean isQuitCommand(String input) {
        return input.equalsIgnoreCase("Q");
    }

    private boolean isInputHangUpCommand(String input) {
        return input.equalsIgnoreCase("H");
    }


    public void setModelView(IModelView modelView){
     presentersManager.setModelView(modelView);
    }
    public void setError(IResponse error)
    {
        presentersManager.setError(error);
    }

    @Override
    public boolean executeCommand(IRequest request) {
        String command=request.getContent();
        return executeCommand(command);
    }

    public void setPersistenceType(){
        IPersistence persistence=system.getPersistence();
        IResponse response=new PersistenceResponse(persistence);
        presentersManager.setPersistenceType(response);
    }

   public boolean changePeristence()
   {
       setNewPersistence();
       setPersistenceType();
       return true;
   }

    private void setNewPersistence() {
        IPersistence actualPersistence=system.getPersistence();
        int MAILBOX_COUNT=system.getMailBoxCount();
        IPersistence newPersistence=null;
        if (isADatabase(actualPersistence))
        {
         newPersistence=new Memory();
        }
        if (isAMemory(actualPersistence))
        {
            newPersistence=new Database();
        }
        system = new MailSystem(MAILBOX_COUNT,newPersistence);
    }

    private boolean isAMemory(IPersistence actualPersistence) {
        return actualPersistence instanceof Memory;
    }

    private boolean isADatabase(IPersistence actualPersistence) {
        return actualPersistence instanceof Database;
    }
}











