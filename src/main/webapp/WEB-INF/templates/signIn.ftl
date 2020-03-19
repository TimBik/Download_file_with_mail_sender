<#include "base.ftl">

<#macro title>login</#macro>

<#macro content>
    <form action="/signIn" method="post">
        <div class="row">
            <div class="rounded col-md-12 background-login" align="center">
                <div class="text-red">
                    <span>Login</span>

                    <div class="form-group">
                        <label for="exampleInputEmail1">Name<span class="required">*</span></label>
                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                               placeholder="Enter email" name="login">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password<span class="required">*</span></label>
                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"
                               name="password">
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberMe">
                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-danger" value="save">Login</button>
            </div>
            <div class="col"></div>
        </div>
    </form>
</#macro>

<@main/>