public class Controller implements IController {

    private IConnection connection;
    public Controller(IConnection connection)
    {
        this.connection=connection;
    }

    @Override
    public boolean executeRequest(String command) {
        IRequest request=new ExecuteCommandRequest(command);
        return connection.executeCommand(request);
    }

}
