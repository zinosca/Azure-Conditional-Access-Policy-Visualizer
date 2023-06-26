package azure.conditional.accesscontrols;

import azure.conditional.accesscontrols.Grant.Grant;
import azure.conditional.accesscontrols.Session.Session;

public class AccessControl {
    private final Grant grant;
    private final Session session;

    public AccessControl(Grant grant, Session session) {
        this.grant = grant;
        this.session = session;
    }

    public Grant getGrant() {
        return grant;
    }

    public Session getSession() {
        return session;
    }
}
