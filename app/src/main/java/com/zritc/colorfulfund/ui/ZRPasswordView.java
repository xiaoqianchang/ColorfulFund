package com.zritc.colorfulfund.ui;

public interface ZRPasswordView {

    enum PasswordType {
        NUMBER, TEXT, TEXTVISIBLE, TEXTWEB;
    }

    //void setError(String error);

    String getPassWord();

    void clearPassword();

    void setPassword(String password);

    void setPasswordVisibility(boolean visible);

    void togglePasswordVisibility();

    void setOnPasswordChangedListener(ZRGridPasswordView.OnPasswordChangedListener listener);

    void setPasswordType(PasswordType passwordType);
}
