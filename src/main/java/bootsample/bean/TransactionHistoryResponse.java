package bootsample.bean;

import java.util.List;

/**
 * Created by adarsh on 05/07/17.
 */
public class TransactionHistoryResponse {
    public List<TransactionBean> transactiondata;
    public Error error;

    public class Error {
        public String[] base;
    }
}
