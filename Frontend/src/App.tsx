import { useEffect, useState } from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import api from "./services/api";
import Login from "./pages/Login/Login";
import Register from "./pages/Register/Register";

function Home() {
    const [message, setMessage] = useState<string>("");

    useEffect(() => {
        api.get<string>("/hello")
            .then((res) => setMessage(res.data))
            .catch(() => setMessage("Backend not reachable"));
    }, []);

    return <h1>{message}</h1>;
}

export default function App() {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            {/* optional: redirect unknown routes */}
            <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
    );
}
