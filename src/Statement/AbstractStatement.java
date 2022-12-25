package Statement;

/**
 * Statement
 * Create by pmtoan
 * Date 12/12/2022 - 4:58 PM
 * Description: ...
 */
public abstract interface IStatement {
    public IStatement select(){
        return null;
    };
    public IStatement select(String[] column){
        return null;
    };
    public IStatement insert(){
        return null;
    };
    public IStatement update(){
        return null;
    };
    public IStatement delete(){
        return null;
    };
}
