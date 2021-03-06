// Copyright 2019 The Nomulus Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package google.registry.gradle.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Plugin setting up the GcsReportUploader task.
 *
 * <p>It goes over all the tasks in a project and pass them on to the GcsReportUploader task for set
 * up.
 *
 * <p>Note that since we're passing in all the projects' tasks - this includes the GcsReportUploader
 * itself! It's up to the GcsReportuploader to take care of not having "infinite loops" caused by
 * waiting for itself to end before finishing.
 */
public class GcsReportUploaderPlugin implements Plugin<Project> {

  public void apply(Project project) {
    GcsReportUploader reportUploader =
        project.getTasks().create("gcsReportUploader", GcsReportUploader.class, task -> {
          task.setDescription("Uploads the reports to GCS bucket");
          task.setGroup("uploads");
        });

    reportUploader.setProject(project);
  }
}
