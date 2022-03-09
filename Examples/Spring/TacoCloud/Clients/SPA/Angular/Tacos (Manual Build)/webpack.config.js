const
    path = require('path'),
    webpack = require('webpack'),
    HtmlWebpackPlugin = require('html-webpack-plugin'),
    MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {

    mode: 'development',

    entry: {

        'polyfills': './src/polyfills.ts',
        'app': './src/main.ts'
    },

    cache: false,

    output: {

        path: path.resolve(__dirname, 'dist'),
        publicPath: '/',
        filename: '[name].js'
    },

    /*
    devServer: {
        historyApiFallback: true,
        port: 8081,
        open: false,
        static: {
            directory: path.join(__dirname, 'dist')
        }
    },*/

    resolve: {
        extensions: ['.ts', '.js']
    },

    module: {
        rules: [
            {
                test: /\.ts$/,
                use: [
                    {
                        loader: 'ts-loader',
                        options: {
                            configFile: path.resolve(__dirname, 'tsconfig.json')
                        }
                    },
                    'angular2-template-loader'
                ]
            },
            {
                test: /\.html$/,
                loader: 'html-loader'
            },
            {
                test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|ico)$/,
                loader: 'file-loader',
                options: {
                    name: '[name].[ext]'
                }
            },
            {
                test: /\.css$/,
                exclude: path.resolve(__dirname, 'src/app'),
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader'
                ]
            },
            {
                test: /\.css$/,
                include: path.resolve(__dirname, 'src/app'),
                loader: 'raw-loader'
            }
        ]
    },

    plugins: [
        new webpack.ContextReplacementPlugin(
            /angular(\\|\/)core/,
            path.resolve(__dirname, 'src'),
            {}
        ),
        new HtmlWebpackPlugin(
            {
                template: 'src/index.html'
            }
        ),
        new MiniCssExtractPlugin(
            {
                filename: '[name].css'
            }
        ),
        new webpack.NoEmitOnErrorsPlugin(),
        new webpack.LoaderOptionsPlugin({
            htmlLoader: {
                minimize: false
            }
        })
    ]
}