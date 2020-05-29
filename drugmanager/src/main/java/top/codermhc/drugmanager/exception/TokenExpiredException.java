package top.codermhc.drugmanager.exception;

public class TokenExpiredException extends DrugmanagerException{

    public TokenExpiredException() {
        super("授权失效");
    }
}
