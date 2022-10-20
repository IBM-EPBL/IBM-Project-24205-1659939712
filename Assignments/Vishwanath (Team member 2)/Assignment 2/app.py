from flask import Flask, render_template

app = Flask(__name__)

@app.route("/signin")
def loginpage():
    return render_template("SigninPage.html")

@app.route("/home")
def homepage():
    return render_template("homePage.html")

@app.route("/signup")
def signuppage():
    return render_template("SignupPage.html")

@app.route("/about")
def aboutpage():
    return render_template("aboutPage.html")