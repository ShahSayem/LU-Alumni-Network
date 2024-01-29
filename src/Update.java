public class Update {
    static private String updateType, updateVal;
    public Update(){};
    public Update(int s, String val)
    {
        if(s == 2) updateType = val;
        else if(s == 3) updateVal = val;
    }
    String getUpdateType()
    {
        return updateType;
    }
    String getUpdateVal()
    {
        return updateVal;
    }
}
