# AWS S3 Static Website

Creating an S3 bucket static website is a common task for hosting a simple, static web application on Amazon Web Services (AWS). Here's a step-by-step task description:

**Task: Creating an S3 Bucket Static Website**

**Objective:** Host a static website using an Amazon S3 bucket.

**Requirements:**
- An AWS account with appropriate permissions.
- Static web content (HTML, CSS, JavaScript, images, etc.) ready for upload.

**Steps:**

1. **Log in to the AWS Management Console:**
   - Open a web browser and go to the [AWS Management Console](https://aws.amazon.com/console/).
   - Sign in with your AWS account credentials.

2. **Navigate to Amazon S3:**
   - In the AWS Management Console, search for and select "S3" under "Storage."

3. **Create a New S3 Bucket:**
   - Click the "Create bucket" button.
   - Choose a unique and descriptive name for your bucket. This name will also be part of your website URL (e.g., `my-static-website-bucket`).
   - Select the AWS region where you want to create the bucket. Choose the region that's closest to your target audience for faster content delivery.
   - Click "Next."

4. **Configure Bucket Properties:**
   - In the "Configure options" section, you can set additional options like versioning, logging, and tags. For a basic static website, you can skip this step by clicking "Next."

5. **Set Permissions:**
   - In the "Set permissions" section, you need to configure the bucket's public access settings to make your website content accessible. Click "Next" after configuring.

6. **Review and Create:**
   - Review your bucket configuration settings.
   - If everything looks correct, click the "Create bucket" button to create your S3 bucket.

7. **Upload Website Content:**
   - After creating the bucket, navigate into it by clicking on its name.
   - Click the "Upload" button to upload your static website content (HTML, CSS, JavaScript, etc.) into the bucket. Make sure to configure the files for public access during the upload process.

8. **Enable Static Website Hosting:**
   - In the S3 bucket's management pane, go to the "Properties" tab.
   - Scroll down to the "Static website hosting" section.
   - Click the "Edit" button.
   - Select "Use this bucket to host a website."
   - Enter the index document (e.g., `index.html`) and optionally, the error document (e.g., `error.html`) if you have one.
   - Click "Save changes."

9. **Configure Bucket Policy for Public Access:**
   - In the S3 bucket's management pane, go to the "Permissions" tab.
   - Click the "Bucket Policy" button.
   - Add a bucket policy that grants public read access to your objects. Here's an example policy:

   ```json
   {
       "Version": "2012-10-17",
       "Statement": [
           {
               "Sid": "PublicReadGetObject",
               "Effect": "Allow",
               "Principal": "*",
               "Action": "s3:GetObject",
               "Resource": "arn:aws:s3:::your-bucket-name/*"
           }
       ]
   }
   ```

   Replace `"your-bucket-name"` with the name of your S3 bucket.
   - Click "Save changes."

10. **Access Your Website:**
    - Once your bucket is configured and your website content is uploaded, you can access your static website using the endpoint provided in the "Static website hosting" section of your bucket's properties.

Your static website is now hosted on Amazon S3, and it should be accessible via the provided endpoint. 
As a proof of succefully completed task should be provided URL of created static website, like follows:

http://singlepage-webapp.s3-website.eu-central-1.amazonaws.com/
