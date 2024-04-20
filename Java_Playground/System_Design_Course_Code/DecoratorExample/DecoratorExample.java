public class DecoratorExample {
    public static void main(String[] args) {
        WebPage webPage = new BasicWebPage();
        webPage = new AuthorizedWebPage(webPage);
        webPage = new AuthenticationWebPage(webPage);
        webPage.display();
    }
}

/* ********************************************************************* */

interface WebPage {
    public void display();
}

class BasicWebPage implements WebPage {
    private String html;
    private String css;
    private String js;

    public void display() {
        System.out.println("Displaying Basic Web Page");
        return;
    }
}

abstract class WebPageDecorator implements WebPage {
    protected WebPage webPage;

    public WebPageDecorator(WebPage webPage) {
        this.webPage = webPage;
    }

    public void display() {
        this.webPage.display();
    }
}

class AuthorizedWebPage extends WebPageDecorator {
    public AuthorizedWebPage(WebPage decoratedPage) {
        super(decoratedPage);
    }

    public void authorizedUser() {
        System.out.println("Authorizing User...");
        System.out.println("User Authorized");
    }

    public void display() {
        super.display();
        this.authorizedUser();
    }
}

class AuthenticationWebPage extends WebPageDecorator {
    public AuthenticationWebPage(WebPage decoratedPage) {
        super(decoratedPage);
    }

    public void authorizedUser() {
        System.out.println("Checking authentication credentials...");
        System.out.println("User authenticated");
    }

    public void display() {
        super.display();
        this.authorizedUser();
    }
}