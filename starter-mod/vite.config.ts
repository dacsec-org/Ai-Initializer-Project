import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  css: {
    preprocessorOptions: {
      scss: {
        additionalData: `@import "./src/styles/variables.scss";`
      }
    }
  },
  server: {
    port: 30320, // Specifies the dev server port
    open: true, // Automatically opens the browser when the server starts
  },
  build: {
    outDir: 'generated', // Specifies the output directory for the build
    sourcemap: true, // Generate source maps for easier debugging
    target: 'esnext', // Optimize build for modern environments
    rollupOptions: {
      input: 'src/main/frontend/pages/@index.tsx', // Ensure the entry
      // point is set to the correct TypeScript entry file in the project
    },
  }
});
