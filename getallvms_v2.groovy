pipeline {
    agent any

    parameters {
        string(name: 'PYTHON_PATH', defaultValue: '/Users/bbharathan/opt/anaconda3/bin/python', description: 'Path to Python executable')
        string(name: 'SCRIPT_PATH', defaultValue: 'getallvms.py', description: 'Path to Python script')
    }

    stages {
        stage('Setup') {
            steps {
                script {
                    // Print current working directory
                    sh 'pwd'

                    // Set the PATH to include the Anaconda3 Python path
                    ENV['PATH'] = "${params.PYTHON_PATH}:${ENV['PATH']}"

                    // Install the pyVmomi Python package using pip3
                    sh 'pip3 install pyVmomi'
                }
            }
        }

        stage('Run Script') {
            steps {
                script {
                    // Run the Python script
                    sh "python3 ${params.SCRIPT_PATH}"
                }
            }
        }
    }
}
