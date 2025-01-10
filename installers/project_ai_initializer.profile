# /etc/apparmor.d/usr.bin.project-ai-initializer

#include <tunables/global>

/opt/project-ai-initializer/start.sh {
  # Include the default AppArmor rules
  #include <abstractions/base>

  # Allow read access to configuration files
  /etc/project-ai-initializer/** r,
  /etc/security/project-ai-initializer.token r,

  # Allow read/write access to log files
  /var/log/project-ai-initializer/** rw,

  # Allow read/write access to runtime files
  /var/run/project-ai-initializer/** rw,

  # Allow read/write access to cache directory
  /home/*/.project-ai-initializer/** rw,
  /home/*/project-ai-initializer.cache/** rw,

  # Allow execution of the start script
  /opt/project-ai-initializer/start.sh rix,

  # Allow execution of binaries in the project directory
  /opt/project-ai-initializer/** rix,

  # Allow network access
  network inet stream,
  network inet dgram,
}
