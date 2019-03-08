package lsieun.standard;

import java.util.ArrayList;
import java.util.List;

import lsieun.utils.ByteUtils;
import lsieun.utils.StringUtils;

public class Entry extends OneThing {
    private EntryType type;
    private byte[] bytes;
    private List<OneThing> members;
    private Container parent;

    // region getters and setters
    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public List<OneThing> getMembers() {
        return members;
    }

    public void setMembers(List<OneThing> members) {
        this.members = members;
    }

    public Container getParent() {
        return parent;
    }

    public void setParent(Container parent) {
        this.parent = parent;
    }

    // endregion

    @Override
    public String toString() {
        if(this.type == EntryType.ONE_ENTRY) {
            List<String> list = new ArrayList();
            list.add("HexCode='" + ByteUtils.toHex(bytes) +"'");

            String content = StringUtils.list2str(list, ", ");

            StringBuilder sb = new StringBuilder();
            sb.append(this.name + ": {");
            sb.append(content);
            sb.append("}");

            return sb.toString();
        }
        else if(this.type == EntryType.MULTI_ENTRIES) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
            for(int i=0; i<this.members.size(); i++) {
                OneThing oneThing = this.members.get(i);
                sb.append(oneThing.toString() + "\r\n");
            }
            return sb.toString();
        }
        else if(this.type == EntryType.ONE_CONTAINER) {
            OneThing oneThing = this.members.get(0);
            return this.name + " " + oneThing.toString();
        }
        else if(this.type == EntryType.MULTI_CONTAINERS) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
//            for(int i=0; i<this.members.size(); i++) {
//                OneThing oneThing = this.members.get(i);
//                sb.append(oneThing.toString() + "\r\n");
//            }
            return sb.toString();
        }
        else {
            return "EntryType ERROR: " + this.type;
        }

    }
}
