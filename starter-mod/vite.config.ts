import path from "node:path";
import type { UserConfigFn } from "vite";
import { overrideVaadinConfig } from "./vite.generated";

const customConfig: UserConfigFn = (env) => ({
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src/main/frontend"),
    },
  },
});

export default overrideVaadinConfig(customConfig);
