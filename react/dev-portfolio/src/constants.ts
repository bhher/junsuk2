import { Project, Skill, Experience, Education } from './types';

export const PROJECTS: Project[] = [
  {
    id: 1,
    title: "AI Chat Platform",
    description: "A real-time chat application powered by Gemini API with markdown support and code highlighting.",
    image: "https://picsum.photos/seed/aichat/800/600",
    tags: ["React", "TypeScript", "Gemini API", "Tailwind"],
    githubUrl: "https://github.com",
    liveUrl: "https://example.com"
  },
  {
    id: 2,
    title: "E-commerce Dashboard",
    description: "A comprehensive admin dashboard for managing products, orders, and analytics with interactive charts.",
    image: "https://picsum.photos/seed/dashboard/800/600",
    tags: ["Next.js", "Recharts", "Prisma", "PostgreSQL"],
    githubUrl: "https://github.com"
  },
  {
    id: 3,
    title: "Travel Planner App",
    description: "A mobile-responsive web app for planning trips, discovering local attractions, and managing itineraries.",
    image: "https://picsum.photos/seed/travel/800/600",
    tags: ["React", "Google Maps API", "Firebase"],
    githubUrl: "https://github.com"
  }
];

export const SKILLS: Skill[] = [
  { name: "React", icon: "Code2" },
  { name: "TypeScript", icon: "FileJson" },
  { name: "Tailwind CSS", icon: "Palette" },
  { name: "Node.js", icon: "Server" },
  { name: "PostgreSQL", icon: "Database" },
  { name: "Git", icon: "GitBranch" },
  { name: "Figma", icon: "Figma" },
  { name: "Motion", icon: "Zap" }
];

export const EXPERIENCES: Experience[] = [
  {
    company: "Tech Innovators Inc.",
    position: "Senior Frontend Developer",
    period: "2022 - Present",
    description: "Leading the frontend development of a high-traffic SaaS platform, focusing on performance and accessibility."
  },
  {
    company: "Creative Solutions Agency",
    position: "Web Developer",
    period: "2020 - 2022",
    description: "Developed custom web applications for various clients, ranging from startups to enterprise companies."
  }
];

export const EDUCATION: Education[] = [
  {
    school: "Global Tech University",
    degree: "B.S. in Computer Science",
    period: "2016 - 2020"
  }
];

export const SOCIAL_LINKS = {
  github: "https://github.com",
  linkedin: "https://linkedin.com",
  blog: "https://dev.to",
  email: "bhher30@gmail.com"
};
