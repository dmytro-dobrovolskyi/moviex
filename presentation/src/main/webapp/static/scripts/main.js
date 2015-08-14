require.config({

    paths: {
        'domReady': '../lib/requirejs-domready/domReady',
        'angular': '../lib/angular/angular'
    },

    shim: {
        'angular': {
            exports: 'angular'
        }
    },

    deps: ['./bootstrap']
});
