<%-- 
    Document   : login
    Created on : 10/05/2023, 10:13:51 a. m.
    Author     : ADMIN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
    <center><div class="container mt-5">
            <form method="post" action="/Login/UsuarioControllador">
                <div class="card">
                    <div class="card-body">
                        <h2>Iniciar Sesion</h2>
                        <input type="text" name="txtemail" class="form-control mt-2" placeholder="Ingrese su email">
                        <input type="password" name="txtpassword" class="form-control mt-2" placeholder="Ingrese su contraseña">
                        <input type="submit" name="btn-login" class="btn btn-primary btn-block mt-2" value="Ingresar">
                    </div>
                </div>
            </form>
        </div>
    </center>
</body>
</html>
