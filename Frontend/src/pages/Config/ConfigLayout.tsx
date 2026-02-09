import { ReactNode } from "react";
import { NavLink } from "react-router-dom";
import AppTopbar from "../../components/AppTopbar/AppTopbar.tsx";
import AppFooter from "../../components/AppFooter/AppFooter.tsx";
import "./Config.css";

type ConfigSideType = "app-settings" | "quote-data" | "quote-config" | "user-admin";
type QuoteDataTab = "products" | "components" | "questions" | "rates" | "wordings";

type ConfigLayoutProps = {
    title: string;
    children: ReactNode;
    activeSide?: ConfigSideType;
    activeTab?: QuoteDataTab;
};

function ConfigLayout({ title, children, activeSide, activeTab }: ConfigLayoutProps) {
    return (
        <div className="config-page">
            <AppTopbar />
            <div className="config-body">
                <aside className="config-sidebar">
                    <NavLink
                        className={`config-side-link${activeSide === "app-settings" ? " config-side-active" : ""}`}
                        to="/config/app-settings"
                    >
                        APP Settings
                    </NavLink>

                    <NavLink
                        className={`config-side-link${activeSide === "quote-data" ? " config-side-active" : ""}`}
                        to="/config/quote-data/products"
                    >
                        Quote Data
                    </NavLink>

                    <NavLink
                        className={`config-side-link${activeSide === "quote-config" ? " config-side-active" : ""}`}
                        to="/config/quote-config"
                    >
                        Quote Configuration
                    </NavLink>

                    <NavLink
                        className={`config-side-link${activeSide === "user-admin" ? " config-side-active" : ""}`}
                        to="/config/user-admin"
                    >
                        User Administration
                    </NavLink>


                </aside>

                <main className="config-main">
                    <div className="config-header">
                        <h1>{title}</h1>
                    </div>

                    {activeSide === "quote-data" && (
                        <div className="config-tabs">
                            <NavLink className={`config-tab${activeTab === "products" ? " config-tab-active" : ""}`} to="/config/quote-data/products">Products</NavLink>
                            <NavLink className={`config-tab${activeTab === "components" ? " config-tab-active" : ""}`} to="/config/quote-data/components">Components</NavLink>
                            <NavLink className={`config-tab${activeTab === "questions" ? " config-tab-active" : ""}`} to="/config/quote-data/questions">Questions</NavLink>
                            <NavLink className={`config-tab${activeTab === "rates" ? " config-tab-active" : ""}`} to="/config/quote-data/rates">Rates</NavLink>
                            <NavLink className={`config-tab${activeTab === "wordings" ? " config-tab-active" : ""}`} to="/config/quote-data/wordings">Wordings</NavLink>
                        </div>
                    )}
                    {children}
                </main>
            </div>
            <AppFooter />
        </div>
    );
}

export default ConfigLayout;