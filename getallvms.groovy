pipeline {
    agent any

    stages {
        stage('Setup') {
            steps {
                script {
                    // Print current working directory
                    sh 'pwd'

                    // Set the PATH to include the Anaconda3 Python path
                    env.PATH = "/Users/bbharathan/opt/anaconda3/bin/python:${env.PATH}"

                    // Install the pyVmomi Python package using pip3
                    sh 'pip3 install pyVmomi'
                }
            }
        }

        stage('Run Script') {
            steps {
                script {
                    // Run the Python script
                    sh 'python3 getallvms.py'
                }
            }
        }
    }
}

