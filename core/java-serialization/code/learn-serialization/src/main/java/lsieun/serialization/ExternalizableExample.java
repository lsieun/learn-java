package lsieun.serialization;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Date;

public class ExternalizableExample implements Externalizable {
    private String name;
    private int age;
    private Date birthDate;

    //region Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    //endregion

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(getName());
        out.writeInt(getAge());
        out.writeObject(getBirthDate());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setName(in.readUTF());
        setAge(in.readInt());
        setBirthDate((Date) in.readObject());
    }

    @Override
    public String toString() {
        return "ExternalizableExample{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                '}';
    }

    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        String filepath = workingDir + File.separator + "target/object.ext";

        ExternalizableExample instance = new ExternalizableExample();
        instance.setName("云天之巔");
        instance.setAge(2);
        instance.setBirthDate(new Date());
        System.out.println(instance);

        try (
                FileOutputStream fout = new FileOutputStream(filepath);
                ObjectOutputStream out = new ObjectOutputStream(fout);
        ) {
            out.writeObject(instance);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileInputStream fin = new FileInputStream(filepath);
                ObjectInputStream in = new ObjectInputStream(fin);
        ) {
            ExternalizableExample another = (ExternalizableExample) in.readObject();
            System.out.println(another);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
