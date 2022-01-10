package Entity;

public class FriendList {

    private String relativename;
    private String tablename;
    private int tableid, adminid, relativeid;
    private int adminstate, relativestate;

    public FriendList(String relativename, String tablename, int tableid, int adminid, int relativeid, int adminstate, int relativestate) {
        this.relativename = relativename;
        this.tablename = tablename;
        this.tableid = tableid;
        this.adminid = adminid;
        this.relativeid = relativeid;
        this.adminstate = adminstate;
        this.relativestate = relativestate;
    }

    public String getRelativename() {
        return relativename;
    }

    public String getTablename() {
        return tablename;
    }

    public int getTableid() {
        return tableid;
    }

    public int getAdminid() {
        return adminid;
    }

    public int getRelativeid() {
        return relativeid;
    }

    public int getAdminstate() {
        return adminstate;
    }

    public int getRelativestate() {
        return relativestate;
    }

}
