import java.util.ArrayList;

public class MessageQueue
{
   private ArrayList<Message>queue;


   public Message peek()
   {
      if (queue.size() == 0) return null;
      else return queue.get(queue.size()-1);
   }
   public MessageQueue()
   {
      queue = new ArrayList();
   }
   public Message remove()
   {
      return queue.remove(queue.size()-1);
   }
   public Message getMessageOf(int position){
      return queue.get(position);
   }
   public void add(Message newMessage)
   {
      queue.add(newMessage);
   }
   public int size()
   {
      return queue.size();
   }


}
