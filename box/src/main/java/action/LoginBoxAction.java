package action;

import config.ConfigBox;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginBoxAction {

    public String getBoxRedirect() {

        return ConfigBox.box_redirect
                + "?response_type=code"
                + "&client_id=" + ConfigBox.client_id
                + "&redirect_uri=" + ConfigBox.redirect_uri;
    }
}
