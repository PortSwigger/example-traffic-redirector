package burp;

public class BurpExtender implements IBurpExtender, IHttpListener
{
    private static final String HOST_FROM = "host1.example.org";
    private static final String HOST_TO = "host2.example.org";
    
    private IExtensionHelpers helpers;
    
    //
    // implement IBurpExtender
    //
    
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        // obtain an extension helpers object
        helpers = callbacks.getHelpers();
        
        // set our extension name
        callbacks.setExtensionName("Traffic redirector");
        
        // register ourselves as an HTTP listener
        callbacks.registerHttpListener(this);
    }
    
    //
    // implement IHttpListener
    //

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo)
    {
        // only process requests
        if (messageIsRequest)
        {
            // get the HTTP service for the request
            IHttpService httpService = messageInfo.getHttpService();
            
            // if the host is HOST_FROM, change it to HOST_TO
            if (HOST_FROM.equalsIgnoreCase(httpService.getHost()))
                messageInfo.setHttpService(helpers.buildHttpService(
                        HOST_TO, httpService.getPort(), httpService.getProtocol()));
        }
    }
}