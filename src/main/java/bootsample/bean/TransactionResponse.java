package bootsample.bean;


/**
 * Created by adarsh on 05/07/17.
 */
public class TransactionResponse {
    public TransactionBean transactionData;
    public Error error;

    public class Error {
        public String[] base;
    }
}
