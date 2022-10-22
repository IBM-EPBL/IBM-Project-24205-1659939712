from flask import Flask, render_template, request, redirect, url_for 
from connect import *

from flask import Flask,redirect,url_for,render_template,request
import ibm_boto3
from ibm_botocore.client import Config, ClientError

COS_ENDPOINT="https://s3.jp-tok.cloud-object-storage.appdomain.cloud"
COS_API_KEY_ID="lUGPUeSdn3ehlIw0G_4JFaKqYFJOg1TlHUJXlVCeUA-Z"



# Create resource https://s3.ap.cloud-object-storage.appdomain.cloud
cos = ibm_boto3.resource("s3",
    ibm_api_key_id=COS_API_KEY_ID,
    config=Config(signature_version="oauth"),
    endpoint_url=COS_ENDPOINT
)


app = Flask(__name__)
 
@app.route('/') 
def assistant_page():
 return render_template('assistant.html')

if(__name__=='__main__'): 
    app.run()
