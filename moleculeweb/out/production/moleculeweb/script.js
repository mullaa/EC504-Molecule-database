feather.replace()

function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object
    for (var i = 0, f; f = files[i]; i++) {
        var reader = new FileReader();
        reader.onload = (function (theFile) {
            return function (e) {
                var span = document.createElement('span');
                span.innerHTML = e.target.result;
                document.getElementById('list').insertBefore(span, null);
            };
        })(f);
        reader.readAsText(f);
    }
}


document.getElementById('files').addEventListener('change', handleFileSelect, false);
