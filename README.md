# Sample Burp Suite extension: traffic redirector

This extension demonstrates how to redirect outgoing HTTP requests from one
host to another. This task might arise, for example, if you have mapped out an
application which then moves to a different staging URL. By simply redirecting
traffic to the new hostname, you can continue to drive your testing from the
original site map.

The extension works as follows:
- It registers itself as an HTTP listener.
- For outgoing request messages, it retrieves the HTTP service for the request.
- If the HTTP service host matches the "from" host, it uses a helper method to
  build a new HTTP service using the "to" host, and other details unchanged.
- It updates the HTTP request with the new HTTP service.

Note: The sample code uses "host1.example.org" and "host2.example.org" as the
"from" and "to" hostnames. You should edit the code to use your own hostnames
before using it.

This repository includes source code for Java, Python and Ruby.
