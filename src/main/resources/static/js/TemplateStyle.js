$(document).ready(
    function(){$("#fishnav").html(
    '<div class="container-fluid">\n' +
    '    <a class="navbar-brand" href="/index">Salted Fish Library</a>\n' +
    '    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02"\n' +
    '            aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation"\n' +
    '            onclick="showSideList()">\n' +
    '      <span class="navbar-toggler-icon"></span>\n' +
    '    </button>\n' +
    '    <div class="collapse navbar-collapse" id="navbarColor02">\n' +
    '      <ul class="navbar-nav me-auto">\n' +
    '        <li class="nav-item">\n' +
    '          <a class="nav-link active" href="/booklist">Library\n' +
    '            <span class="visually-hidden">(current)</span>\n' +
    '          </a>\n' +
    '        </li>\n' +
    '        <li class="nav-item dropdown">\n' +
    '          <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"\n' +
    '             href="#" role="button" aria-haspopup="true" aria-expanded="false" onclick="showDropdown(\'1\')">Services</a>\n' +
    '          <div class="dropdown-menu" id="dropdown1">\n' +
    '            <a class="dropdown-item" th:href="@{/profile#t_2}">Read plans</a>\n' +
    '            <a class="dropdown-item" th:href="@{/profile#t_3}">Reservation</a>\n' +
    '            <a class="dropdown-item" href="#">Wish List(Tell us the book you want)å</a>\n' +
    '          </div>\n' +
    '        </li>\n' +
    '        <li class="nav-item">\n' +
    '          <a class="nav-link" href="#">Guidance</a>\n' +
    '        </li>\n' +
    '        <li class="nav-item">\n' +
    '          <a class="nav-link" href="#">About</a>\n' +
    '        </li>&nbsp;&nbsp;&nbsp;&nbsp;\n' +
    '        <li>\n' +
    '          <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" id="username"\n' +
    '             href="#" role="button" aria-haspopup="true" aria-expanded="false" onclick="showDropdown(\'2\')">username</a>\n' +
    '          <div class="dropdown-menu" id="dropdown2">\n' +
    '            <a class="dropdown-item" href="/profile">Profile</a>\n' +
    '            <a class="dropdown-item" th:href="@{/profile#t_5}">ID QRcode</a>\n' +
    '            <a class="dropdown-item" href="#">Setting</a>\n' +
    '          </div>\n' +
    '        </li>\n' +
    '      </ul>\n' +
    '      <form class="d-flex">\n' +
    '        <input class="form-control me-sm-2" type="text" placeholder="Search" id="queryText0">\n' +
    '        <button class="btn btn-secondary my-2 my-sm-0" type="button" onclick="searchHerf($(\'#queryText0\').val())">Search</button>\n' +
    '      </form>\n' +
    '      <a href="/login" id="login-btn" class="nav-link active">\n' +
    '        <input type="button" value="Log in" class="btn btn-primary">\n' +
    '      </a>\n' +
    '      <input type="button" value="Log out" class="btn btn-primary" id="logout">\n' +
    '    </div>\n' +
    '  </div>')
    }
)