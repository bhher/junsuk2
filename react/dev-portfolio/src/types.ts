export interface Project {
  id: number;
  title: string;
  description: string;
  image: string;
  tags: string[];
  githubUrl: string;
  liveUrl?: string;
}

export interface Skill {
  name: string;
  icon: string; // Lucide icon name
}

export interface Experience {
  company: string;
  position: string;
  period: string;
  description: string;
}

export interface Education {
  school: string;
  degree: string;
  period: string;
}
