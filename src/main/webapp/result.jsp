<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Result</title>
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
  <body>
    <header>
        <h2>[Info] : ${status}</h2>
    </header>

    <div class="row">
        <div class="column">
            <embed src="${fout}" type="application/pdf" height="100%" width="100%" class="responsive">
        </div>
        <div class="column">
            <h3>pdfTeX log : </h3>
            <textarea rows="50" cols="80">${log}</textarea>
        </div>
    </div>    
    <footer>
      <h2>All rights reserved - https://github.com/AntoineNGUYEN31</h2>
    </footer>
  </body>
</html>