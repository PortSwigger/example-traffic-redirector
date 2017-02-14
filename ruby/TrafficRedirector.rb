require 'java'
java_import 'burp.IBurpExtender'
java_import 'burp.IHttpListener'

class BurpExtender
  include IBurpExtender, IHttpListener
    
  #
  # implement IBurpExtender
  #
  
  def	registerExtenderCallbacks(callbacks)
      
	  @HOST_FROM = 'host1.example.org'
	  @HOST_TO = 'host2.example.org'

    # obtain an extension helpers object
    @helpers = callbacks.getHelpers()
    
    # set our extension name
    callbacks.setExtensionName("Traffic redirector")
    
    # register ourselves as an HTTP listener
    callbacks.registerHttpListener(self)
      
  end

  #
  # implement IHttpListener
  #
  
  def processHttpMessage(toolFlag, messageIsRequest, messageInfo)
      
    # only process requests
    if (messageIsRequest)
    
      # get the HTTP service for the request
      httpService = messageInfo.getHttpService()
      
      # if the host is HOST_FROM, change it to HOST_TO
      if (@HOST_FROM == httpService.getHost())
          messageInfo.setHttpService(@helpers.buildHttpService(@HOST_TO, httpService.getPort(), httpService.getProtocol()))
        end
    end
    
  end

end