<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Latex builder tool</title>
<style>
* {
    box-sizing: border-box;
}
.column {
  float: left;
  width: 50.0%;
  padding: 5px;
}

/* Clearfix (clear floats) */
.row::after {
  content: "";
  clear: both;
  display: table;
}
header {
  background-color: #666;
  padding: 0px;
  text-align: center;
  font-size: 35px;
  color: white;
}
footer {
  background-color: rgb(241, 46, 12);
  padding: 5px;
  font-size: 35px;
  text-align: center;
  color: white;
}
</style>
</head>
<!--body style="background-image: url('background.jpg');"-->
<body>
  <header>
    <h2>LateX Project Builder</h2>
  </header>
  <!--form action="process" method="post">
    <p>
        <textarea name="input1" rows="10" cols="50">${preinput1}</textarea>
    </p>
    <p>
      <input type="submit" name="encode" value="Encode">
      <input type="submit" name="decode" value="Decode">
    </p>
    <p>
      <textarea name="res1"rows="10" cols="50">${res1}</textarea>
    </p>
  </form-->
  
  <div class="row">
    <div class="column">
      <p>
        Cat In LateX is a love story between TOMCAT and LATEX, a SaaS that provides a the possibilty to build your LateX 
        project without installing any LateX tool on your PC. Behind the screen, Tomcat JSP containerized on AWS EC2 is used.
      </p>
      <p>
        Option 1: Please type in your LateX script.
        <form action="process" method="post">
          <p>
            Specify the file name: 
            <input type="text" name="fname" value="hello.tex">
          </p>
          <textarea name="fcontent" rows="10" cols="50">
\documentclass[12pt]{article}
\begin{document}
Hello world!
$Hello world!$ %math mode
\end{document}
          </textarea>
          <p>
            <input type="submit" value="Build">
          </p>
        </form>
      </p>
      <p>
        Option 2: Please upload a single zip file containing your project.<br/>
        Make sure to zip your project without parent folder, e.g. zip -j my.zip myProject/*
        <form action="upload" method="post"
                        enctype="multipart/form-data">
          <input type="file" name="zipfile" size="50" />
          <br />
          <br />
          <input type="submit" value="Build" />
        </form>
      </p>
    </div>
    <div class="column">
      <img src="background.jpg" width="500">
    </div>
  </div>

  <footer>
    <h2>All rights reserved - https://github.com/AntoineNGUYEN31</h2>
  </footer>
</body>
