"use strict";
if (typeof Reveal === "undefined") {
  document.body.innerHTML = '<p role="alert">No se pudo cargar Reveal.js. Comprueba tu conexión y vuelve a abrir la presentación.</p>';
} else {
  Reveal.initialize({
    width: 1280, height: 720, margin: 0.05,
    controls: true, progress: true, slideNumber: "c/t", hash: true,
    keyboard: true, touch: true, overview: true, center: false,
    transition: window.matchMedia("(prefers-reduced-motion: reduce)").matches ? "none" : "slide",
    autoSlide: 0,
    katex: {
      version: "0.16.11",
      delimiters: [
        { left: "\\(", right: "\\)", display: false },
        { left: "\\[", right: "\\]", display: true }
      ],
      ignoredTags: ["script", "noscript", "style", "textarea", "pre", "code"],
      throwOnError: false
    },
    plugins: typeof RevealMath !== "undefined" ? [RevealMath.KaTeX] : []
  });
}
