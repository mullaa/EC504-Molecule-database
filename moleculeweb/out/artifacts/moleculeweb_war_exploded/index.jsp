<%--
  Created by IntelliJ IDEA.
  User: mulla
  Date: 2020/4/12
  Time: 下午11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Molecule Database</title>
    <link rel = "icon" href = "logo.png" type = "image/x-icon">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="resize.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="script/jquery-1.6.2.min.js"></script>
  </head>
  <body>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#">Molecule Database</a>
  </nav>
  <main role="main">
    <div class="jumbotron">
      <div class="container">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
          <li class="nav-item">
            <a class="nav-link" id="files-tab" data-toggle="tab" href="#files"
               role="tab" aria-controls="files" aria-selected="false">Select a File</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="search-tab" data-toggle="tab" href="#search"
               role="tab" aria-controls="search" aria-selected="false">Search</a>
          </li>
        </ul>
        <div class="tab-content card" style="border-top-left-radius: 0rem !important;
        border-top-right-radius: .25rem; border-top: 0px !important;" id="myTabContent">

          <div class="tab-pane fade card-body mt-4" id="files" role="tabpanel" aria-labelledby="files-tab">
            <p>Select a molecule file:</p>
            <div class="custom-file" style="width:500px">
              <form action="/submit" method="POST" enctype="multipart/form-data">
                <input type="file" id="inputfile" name="inputfile" multiple="multiple" value = "Select multiple files" onchange="handleFiles(this.files)"/>
                <button type="submit">Add Molecules</button>
              </form>
              <!-- <input type="file" id="inputfile" name="inputfiles" onchange="handleFiles(this.files)"> -->
              <output id="list"></output>
              <!-- <label class="custom-file-label mt-2 ml-3 mr-3" for="files" id="labelinput"
              style="color: #1D7D72; font-family: 'Avenir next'; font-size:18px">Choose a txt file</label> -->
              <br/>
              <p class="mt-3 text-muted"></p>The input should be as following:</p>
              <p class="mt-3 text-muted"></p>[MOLECULE NAME]</p>
              <p class="mt-3 text-muted"></p>[# OF VERTICES]]</p>
              <p class="mt-3 text-muted"></p>[LABEL OF VERTEX ID 0]</p>
              <p class="mt-3 text-muted"></p>[LABEL OF VERTEX ID 1]</p>
              <p class="mt-3 text-muted"></p>[LABEL OF VERTEX ID 2]</p>
              <p class="mt-3 text-muted"></p>...</p>
              <p class="mt-3 text-muted"></p>[LABEL OF VERTEX ID V]</p>
              <br/>
              <input type = "button" value = "Add Molecule" onclick = "fileimport()"/>
              <!-- <button type="button" class="btn btn-primary">Add Molecule</button> -->
            </div>
          </div>

          <div class="tab-pane fade card-body mt-4" id="search" role="tabpanel" aria-labelledby="search-tab">
            <p>Search for a molecule:</p>
            <div class="custom-file" style="width:500px">
              <input type="search" name = "search1"/>
              <input type="submit" value = "Search for Molecule"/>
              <br/>
            </div>
          </div>
        </div>


      </div>

    </div>

  </main>
  <script src="script.js"></script>
  <script type='text/javascript'>
    function handleFileSelect(evt) {
      const files = evt.target.files; // FileList object
      let i = 0, f;
      for (; f = files[i]; i++) {
        const reader = new FileReader();
        reader.onload = (function (theFile) {
          return function (e) {
            const span = document.createElement('span');
            span.innerHTML = e.target.result;
            document.getElementById('list').insertBefore(span, null);
          };
        })(f);
        reader.readAsText(f);
      }
    }
  </script>
  </body>
</html>
