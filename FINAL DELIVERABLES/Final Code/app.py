from flask import Flask, redirect, url_for, render_template, request
app =Flask(__name__)

@app.route("/")
@app.route("/home")
def home():
    return render_template("home.html")
@app.route("/adminlogin")
def alogin():
    return render_template("admin login.html")
@app.route("/userlogin")
def ulogin():
    return render_template("user login.html")
@app.route("/aboutpage")
def page():
    return render_template("aboutpage.html")
@app.route("/contactus")
def contact():
    return render_template("contact us.html")
@app.route("/success")
def success():
    return render_template("SUCCESS.html")
@app.route("/s2")
def s2():
    return render_template("succ2.html")
@app.route("/addremove")
def addremove():
    return render_template("add&remove.html")
@app.route("/zone")
def zone():
    return render_template("ZONE LIST.html")
@app.route("/service")
def service():
    return render_template("services.html")
@app.route("/email")
def email():
    return render_template("email notify.html")
@app.route("/mobile")
def mobile():
    return render_template("mobilecall.html")
@app.route("/chatbot")
def chatbot():
    return render_template("chatbot.html")
@app.route("/external_links")
def external_links():
    return render_template("external_links.html")
@app.route("/remove")
def remove():
    return render_template("remove list.html")
if __name__=='__main__':
    app.run()
    