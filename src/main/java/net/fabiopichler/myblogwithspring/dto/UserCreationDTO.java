/*-------------------------------------------------------------------------------

Copyright (c) 2023 Fábio Pichler

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

-------------------------------------------------------------------------------*/

package net.fabiopichler.myblogwithspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreationDTO {

    @NotBlank(message = "Name may not be blank")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long")
    private String name;

    @NotBlank(message = "username may not be blank")
    @Size(min = 2, max = 20, message = "username must be between 2 and 20 characters long")
    private String username;

    @NotBlank(message = "Email may not be blank")
    @Size(min = 2, max = 255, message = "Email must be between 2 and 255 characters long")
    private String email;

    @NotBlank(message = "password may not be blank")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long")
    private String password;

    @NotBlank(message = "passwordRepeat may not be blank")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long")
    private String passwordRepeat;

    public UserCreationDTO() {
    }

    public UserCreationDTO(String name, String username, String email, String password, String passwordRepeat) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
