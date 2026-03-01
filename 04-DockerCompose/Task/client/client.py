#!/usr/bin/env python3
import urllib.request
fp = urllib.request.urlopen("http://localhost:8080/")
encodedContent = fp.read()
decodedContent = encodedContent.decode("utf8")
print(decodedContent)
fp.close()
