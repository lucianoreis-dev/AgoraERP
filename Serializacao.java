import java.io.Serializable;

import java.io.IOException;
import java.io.EOFException;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
/**
 * @author : reis
 * @created : 2022-02-14
 **/
class Serializa
{
    public static void main(String[] args)
            throws IOException
        {


            User user1 = new User("usuario@hotmail.com", 2020, 123);

            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("user.ser"));

            oos.writeObject(user1);

            oos.flush();
            oos.close();
        }
}

class Deserializa
{
    public static void main(String[] args)
            throws ClassNotFoundException, IOException
        {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream("user.ser"));

            while(true)
            {
                try {
                    Object obj = ois.readObject();
                    User user = (User) obj;
                    System.out.println(user);
                } catch(EOFException e){
                    System.out.println("Fim de arquivo.");
                    break;
                }
            }
            ois.close();
        }
}

class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String username;
    private int password;
    private transient int pin;

    public User(String username, int password, int pin){
        this.username = username;
        this.password = password;
        this.pin = pin;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(int password){
        this.password = password;
    }

    public void setPint(int pin){
        this.pin = pin;
    }

    public String getUsername(){
        return username;
    }

    public int getPassword(){
        return password;
    }

    public int getPin(){
        return pin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("serialVersionUID = ").append(serialVersionUID);
        sb.append(", username = ").append(getUsername());
        sb.append(", password = ").append(getPassword());
        sb.append(", pin = ").append(getPin());
        return sb.append("}").toString();
    }

    private void readObject(ObjectInputStream in)
        throws ClassNotFoundException, IOException
    {
        in.defaultReadObject();
        this.password = this.password >> 2;
    }

    private void writeObject(ObjectOutputStream out) 
        throws IOException
    {
        this.password = this.password << 2;
        out.defaultWriteObject();
    }
}
