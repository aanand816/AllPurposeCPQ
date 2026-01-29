import { useEffect, useState, type FormEvent } from "react";
import "./Login.css";
import logoAllCpq from "../../assets/logo_allCPQ.png";

 function Login() {
    const [email, setEmail] = useState("");
 const [password, setPassword] = useState("");
 const [error, setError] = useState("");

    useEffect(() => {
        document.title = "allCPQ | Login";
        return () => {
            document.title = "allCPQ";
        };
    }, []);

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (!email || !password) {
            setError("Email and password are required");
            return;
        }

        setError("");
        console.log("Login data:", { email, password });

        // Later:
        // call API → /auth/login
    };

    return (
        <div className="auth-page">
            <div className="auth-shell auth-shell-split">
                <section className="auth-left">
                    <div className="auth-logo">
                        <img src={logoAllCpq} alt="AllCPQ logo" />
                    </div>
                    <h1 className="auth-title">Login to Your ApAdmin Account</h1>
                    <div className="auth-accent-line" />
                </section>

                <form className="auth-card" onSubmit={handleSubmit}>
                    <div className="auth-card-title">Admin User Account</div>

                    <div className="auth-grid auth-grid-two">
                        <label htmlFor="login-username">Username:</label>
                        <input
                            id="login-username"
                            type="text"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />

                        <label htmlFor="login-password">Password:</label>
                        <input
                            id="login-password"
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>

                    {error && <p className="auth-error">{error}</p>}
                    <button className="auth-button" type="submit">Login</button>
                </form>
            </div>
        </div>
    );
}
export default Login;
