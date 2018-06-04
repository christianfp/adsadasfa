public class ExecuteCommandRequest implements IRequest {
    private String Content;

    public ExecuteCommandRequest(String Content) {
        this.Content=Content;
    }

    @Override
    public String getContent() {
        return Content;
    }
}
