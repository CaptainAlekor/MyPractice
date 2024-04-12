import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function LoginForm() {
    const navigate = useNavigate();

    return (
        <div className="login-form-container">
            <form name="loginForm" onSubmit={checkLoginForm}>
                <div>
                    <label>email:</label>
                    <br />
                    <input name="email" type="text"></input>
                </div>
                <div>
                    <label>password:</label>
                    <br />
                    <input name="password" type="password"></input>
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );

    async function checkLoginForm(event) {
        event.preventDefault();
        const email = event.target.email.value;
        const password = event.target.password.value;

        const apiUrl = "auth";
        await axios
            .post(apiUrl, {
                email: email,
                password: password,
            })
            .then((resp) => {
                if (typeof resp.data == "string") {
                    alert(resp.data);
                    return;
                }
                const user = resp.data;
                localStorage.setItem("user", JSON.stringify(user));

                if (user.role === 'ROLE_STUDENT') {
                    navigate("/rating", { replace: false });
                }
                if (user.role === 'ROLE_PROFESSOR') {
                    navigate("/professor-group-courses", { replace: false });
                }
            });
    }
}
